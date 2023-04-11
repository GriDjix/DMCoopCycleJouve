import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './cooperativelocal.reducer';

export const CooperativelocalDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cooperativelocalEntity = useAppSelector(state => state.cooperativelocal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cooperativelocalDetailsHeading">
          <Translate contentKey="coopcycleApp.cooperativelocal.detail.title">Cooperativelocal</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="coopcycleApp.cooperativelocal.id">Id</Translate>
            </span>
          </dt>
          <dd>{cooperativelocalEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.cooperativelocal.name">Name</Translate>
            </span>
          </dt>
          <dd>{cooperativelocalEntity.name}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="coopcycleApp.cooperativelocal.city">City</Translate>
            </span>
          </dt>
          <dd>{cooperativelocalEntity.city}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="coopcycleApp.cooperativelocal.country">Country</Translate>
            </span>
          </dt>
          <dd>{cooperativelocalEntity.country}</dd>
        </dl>
        <Button tag={Link} to="/cooperativelocal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cooperativelocal/${cooperativelocalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CooperativelocalDetail;
