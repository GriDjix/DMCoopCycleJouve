import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICooperativelocal } from 'app/shared/model/cooperativelocal.model';
import { getEntities } from './cooperativelocal.reducer';

export const Cooperativelocal = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const cooperativelocalList = useAppSelector(state => state.cooperativelocal.entities);
  const loading = useAppSelector(state => state.cooperativelocal.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="cooperativelocal-heading" data-cy="CooperativelocalHeading">
        <Translate contentKey="coopcycleApp.cooperativelocal.home.title">Cooperativelocals</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="coopcycleApp.cooperativelocal.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/cooperativelocal/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="coopcycleApp.cooperativelocal.home.createLabel">Create new Cooperativelocal</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cooperativelocalList && cooperativelocalList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="coopcycleApp.cooperativelocal.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.cooperativelocal.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.cooperativelocal.city">City</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.cooperativelocal.country">Country</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cooperativelocalList.map((cooperativelocal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/cooperativelocal/${cooperativelocal.id}`} color="link" size="sm">
                      {cooperativelocal.id}
                    </Button>
                  </td>
                  <td>{cooperativelocal.name}</td>
                  <td>{cooperativelocal.city}</td>
                  <td>{cooperativelocal.country}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/cooperativelocal/${cooperativelocal.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/cooperativelocal/${cooperativelocal.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/cooperativelocal/${cooperativelocal.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="coopcycleApp.cooperativelocal.home.notFound">No Cooperativelocals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Cooperativelocal;
