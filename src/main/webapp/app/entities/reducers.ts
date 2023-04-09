import restaurant from 'app/entities/restaurant/restaurant.reducer';
import order from 'app/entities/order/order.reducer';
import meal from 'app/entities/meal/meal.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  restaurant,
  order,
  meal,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
