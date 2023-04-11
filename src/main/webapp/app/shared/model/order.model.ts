import dayjs from 'dayjs';
import { IMeal } from 'app/shared/model/meal.model';
import { IClient } from 'app/shared/model/client.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

export interface IOrder {
  id?: string;
  orderDate?: string;
  totalPrice?: number;
  status?: OrderStatus;
  deliveryAddress?: string;
  deliveryCity?: string;
  deliveryCountry?: string;
  deliveryTime?: string;
  meals?: IMeal[] | null;
  client?: IClient | null;
  restaurant?: IRestaurant | null;
}

export const defaultValue: Readonly<IOrder> = {};
