import restaurant from 'app/entities/restaurant/restaurant.reducer';
import order from 'app/entities/order/order.reducer';
import meal from 'app/entities/meal/meal.reducer';
import client from 'app/entities/client/client.reducer';
import cooperativelocal from 'app/entities/cooperativelocal/cooperativelocal.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  restaurant,
  order,
  meal,
  client,
  cooperativelocal,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
