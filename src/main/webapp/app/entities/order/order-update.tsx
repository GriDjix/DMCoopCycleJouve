import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IClient } from 'app/shared/model/client.model';
import { getEntities as getClients } from 'app/entities/client/client.reducer';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { getEntities as getRestaurants } from 'app/entities/restaurant/restaurant.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { getEntity, updateEntity, createEntity, reset } from './order.reducer';

export const OrderUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const clients = useAppSelector(state => state.client.entities);
  const restaurants = useAppSelector(state => state.restaurant.entities);
  const orderEntity = useAppSelector(state => state.order.entity);
  const loading = useAppSelector(state => state.order.loading);
  const updating = useAppSelector(state => state.order.updating);
  const updateSuccess = useAppSelector(state => state.order.updateSuccess);
  const orderStatusValues = Object.keys(OrderStatus);

  const handleClose = () => {
    navigate('/order');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getClients({}));
    dispatch(getRestaurants({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.orderDate = convertDateTimeToServer(values.orderDate);
    values.deliveryTime = convertDateTimeToServer(values.deliveryTime);

    const entity = {
      ...orderEntity,
      ...values,
      client: clients.find(it => it.id.toString() === values.client.toString()),
      restaurant: restaurants.find(it => it.id.toString() === values.restaurant.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          orderDate: displayDefaultDateTime(),
          deliveryTime: displayDefaultDateTime(),
        }
      : {
          status: 'CREATED',
          ...orderEntity,
          orderDate: convertDateTimeFromServer(orderEntity.orderDate),
          deliveryTime: convertDateTimeFromServer(orderEntity.deliveryTime),
          client: orderEntity?.client?.id,
          restaurant: orderEntity?.restaurant?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="coopcycleApp.order.home.createOrEditLabel" data-cy="OrderCreateUpdateHeading">
            <Translate contentKey="coopcycleApp.order.home.createOrEditLabel">Create or edit a Order</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="order-id"
                  label={translate('coopcycleApp.order.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('coopcycleApp.order.orderDate')}
                id="order-orderDate"
                name="orderDate"
                data-cy="orderDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.order.totalPrice')}
                id="order-totalPrice"
                name="totalPrice"
                data-cy="totalPrice"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('coopcycleApp.order.status')} id="order-status" name="status" data-cy="status" type="select">
                {orderStatusValues.map(orderStatus => (
                  <option value={orderStatus} key={orderStatus}>
                    {translate('coopcycleApp.OrderStatus.' + orderStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('coopcycleApp.order.deliveryAddress')}
                id="order-deliveryAddress"
                name="deliveryAddress"
                data-cy="deliveryAddress"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.order.deliveryCity')}
                id="order-deliveryCity"
                name="deliveryCity"
                data-cy="deliveryCity"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.order.deliveryCountry')}
                id="order-deliveryCountry"
                name="deliveryCountry"
                data-cy="deliveryCountry"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.order.deliveryTime')}
                id="order-deliveryTime"
                name="deliveryTime"
                data-cy="deliveryTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField id="order-client" name="client" data-cy="client" label={translate('coopcycleApp.order.client')} type="select">
                <option value="" key="0" />
                {clients
                  ? clients.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-restaurant"
                name="restaurant"
                data-cy="restaurant"
                label={translate('coopcycleApp.order.restaurant')}
                type="select"
              >
                <option value="" key="0" />
                {restaurants
                  ? restaurants.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OrderUpdate;
