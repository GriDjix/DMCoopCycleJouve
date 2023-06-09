import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Restaurant from './restaurant';
import Order from './order';
import Meal from './meal';
import Client from './client';
import Cooperativelocal from './cooperativelocal';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="restaurant/*" element={<Restaurant />} />
        <Route path="order/*" element={<Order />} />
        <Route path="meal/*" element={<Meal />} />
        <Route path="client/*" element={<Client />} />
        <Route path="cooperativelocal/*" element={<Cooperativelocal />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
