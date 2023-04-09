import { IRestaurant } from 'app/shared/model/restaurant.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IMeal {
  id?: string;
  name?: string;
  description?: string;
  price?: number;
  restaurant?: IRestaurant | null;
  order?: IOrder | null;
}

export const defaultValue: Readonly<IMeal> = {};
