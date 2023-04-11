import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './client.reducer';

export const ClientDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const clientEntity = useAppSelector(state => state.client.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="clientDetailsHeading">
          <Translate contentKey="coopcycleApp.client.detail.title">Client</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="coopcycleApp.client.id">Id</Translate>
            </span>
          </dt>
          <dd>{clientEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.client.name">Name</Translate>
            </span>
          </dt>
          <dd>{clientEntity.name}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="coopcycleApp.client.email">Email</Translate>
            </span>
          </dt>
          <dd>{clientEntity.email}</dd>
          <dt>
            <span id="password">
              <Translate contentKey="coopcycleApp.client.password">Password</Translate>
            </span>
          </dt>
          <dd>{clientEntity.password}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="coopcycleApp.client.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{clientEntity.phone}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="coopcycleApp.client.address">Address</Translate>
            </span>
          </dt>
          <dd>{clientEntity.address}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="coopcycleApp.client.city">City</Translate>
            </span>
          </dt>
          <dd>{clientEntity.city}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="coopcycleApp.client.country">Country</Translate>
            </span>
          </dt>
          <dd>{clientEntity.country}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.client.coop">Coop</Translate>
          </dt>
          <dd>{clientEntity.coop ? clientEntity.coop.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/client" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client/${clientEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClientDetail;
