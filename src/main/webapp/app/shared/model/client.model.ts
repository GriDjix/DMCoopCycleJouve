import { ICooperativelocal } from 'app/shared/model/cooperativelocal.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IClient {
  id?: string;
  name?: string;
  email?: string;
  password?: string;
  phone?: string;
  address?: string | null;
  city?: string | null;
  country?: string | null;
  coop?: ICooperativelocal | null;
  orders?: IOrder[] | null;
}

export const defaultValue: Readonly<IClient> = {};
