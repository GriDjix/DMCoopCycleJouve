import { IMeal } from 'app/shared/model/meal.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IRestaurant {
  id?: string;
  name?: string;
  description?: string | null;
  address?: string;
  city?: string;
  country?: string;
  phone?: string;
  email?: string;
  meals?: IMeal[] | null;
  orders?: IOrder[] | null;
}

export const defaultValue: Readonly<IRestaurant> = {};
