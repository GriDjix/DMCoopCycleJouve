import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order.reducer';

export const OrderDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderEntity = useAppSelector(state => state.order.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDetailsHeading">
          <Translate contentKey="coopcycleApp.order.detail.title">Order</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="coopcycleApp.order.id">Id</Translate>
            </span>
          </dt>
          <dd>{orderEntity.id}</dd>
          <dt>
            <span id="orderDate">
              <Translate contentKey="coopcycleApp.order.orderDate">Order Date</Translate>
            </span>
          </dt>
          <dd>{orderEntity.orderDate ? <TextFormat value={orderEntity.orderDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="totalPrice">
              <Translate contentKey="coopcycleApp.order.totalPrice">Total Price</Translate>
            </span>
          </dt>
          <dd>{orderEntity.totalPrice}</dd>
          <dt>
            <span id="deliveryAddress">
              <Translate contentKey="coopcycleApp.order.deliveryAddress">Delivery Address</Translate>
            </span>
          </dt>
          <dd>{orderEntity.deliveryAddress}</dd>
          <dt>
            <span id="deliveryCity">
              <Translate contentKey="coopcycleApp.order.deliveryCity">Delivery City</Translate>
            </span>
          </dt>
          <dd>{orderEntity.deliveryCity}</dd>
          <dt>
            <span id="deliveryCountry">
              <Translate contentKey="coopcycleApp.order.deliveryCountry">Delivery Country</Translate>
            </span>
          </dt>
          <dd>{orderEntity.deliveryCountry}</dd>
          <dt>
            <span id="deliveryTime">
              <Translate contentKey="coopcycleApp.order.deliveryTime">Delivery Time</Translate>
            </span>
          </dt>
          <dd>{orderEntity.deliveryTime ? <TextFormat value={orderEntity.deliveryTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.order.user">User</Translate>
          </dt>
          <dd>{orderEntity.user ? orderEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.order.restaurant">Restaurant</Translate>
          </dt>
          <dd>{orderEntity.restaurant ? orderEntity.restaurant.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order/${orderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderDetail;
