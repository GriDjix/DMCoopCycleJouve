import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Cooperativelocal from './cooperativelocal';
import CooperativelocalDetail from './cooperativelocal-detail';
import CooperativelocalUpdate from './cooperativelocal-update';
import CooperativelocalDeleteDialog from './cooperativelocal-delete-dialog';

const CooperativelocalRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Cooperativelocal />} />
    <Route path="new" element={<CooperativelocalUpdate />} />
    <Route path=":id">
      <Route index element={<CooperativelocalDetail />} />
      <Route path="edit" element={<CooperativelocalUpdate />} />
      <Route path="delete" element={<CooperativelocalDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CooperativelocalRoutes;
