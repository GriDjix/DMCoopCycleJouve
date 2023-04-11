import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICooperativelocal } from 'app/shared/model/cooperativelocal.model';
import { getEntities as getCooperativelocals } from 'app/entities/cooperativelocal/cooperativelocal.reducer';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { getEntity, updateEntity, createEntity, reset } from './restaurant.reducer';

export const RestaurantUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cooperativelocals = useAppSelector(state => state.cooperativelocal.entities);
  const restaurantEntity = useAppSelector(state => state.restaurant.entity);
  const loading = useAppSelector(state => state.restaurant.loading);
  const updating = useAppSelector(state => state.restaurant.updating);
  const updateSuccess = useAppSelector(state => state.restaurant.updateSuccess);

  const handleClose = () => {
    navigate('/restaurant');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCooperativelocals({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...restaurantEntity,
      ...values,
      coop: cooperativelocals.find(it => it.id.toString() === values.coop.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...restaurantEntity,
          coop: restaurantEntity?.coop?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="coopcycleApp.restaurant.home.createOrEditLabel" data-cy="RestaurantCreateUpdateHeading">
            <Translate contentKey="coopcycleApp.restaurant.home.createOrEditLabel">Create or edit a Restaurant</Translate>
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
                  id="restaurant-id"
                  label={translate('coopcycleApp.restaurant.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('coopcycleApp.restaurant.name')}
                id="restaurant-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.description')}
                id="restaurant-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.address')}
                id="restaurant-address"
                name="address"
                data-cy="address"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.city')}
                id="restaurant-city"
                name="city"
                data-cy="city"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.country')}
                id="restaurant-country"
                name="country"
                data-cy="country"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.phone')}
                id="restaurant-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: {
                    value: /^(\+\d{1,3}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/,
                    message: translate('entity.validation.pattern', {
                      pattern: '^(\\+\\d{1,3}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$',
                    }),
                  },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.restaurant.email')}
                id="restaurant-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: {
                    value: /^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_-]+)\.([a-zA-Z]{2,5})$/,
                    message: translate('entity.validation.pattern', { pattern: '^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_-]+)\\.([a-zA-Z]{2,5})$' }),
                  },
                }}
              />
              <ValidatedField
                id="restaurant-coop"
                name="coop"
                data-cy="coop"
                label={translate('coopcycleApp.restaurant.coop')}
                type="select"
              >
                <option value="" key="0" />
                {cooperativelocals
                  ? cooperativelocals.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/restaurant" replace color="info">
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

export default RestaurantUpdate;
