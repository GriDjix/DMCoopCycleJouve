import dayjs from 'dayjs';
import { IMeal } from 'app/shared/model/meal.model';
import { IUser } from 'app/shared/model/user.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface IOrder {
  id?: string;
  orderDate?: string;
  totalPrice?: number;
  deliveryAddress?: string;
  deliveryCity?: string;
  deliveryCountry?: string;
  deliveryTime?: string;
  meals?: IMeal[] | null;
  user?: IUser | null;
  restaurant?: IRestaurant | null;
}

export const defaultValue: Readonly<IOrder> = {};
