import { IClient } from 'app/shared/model/client.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface ICooperativelocal {
  id?: string;
  name?: string;
  city?: string;
  country?: string;
  clients?: IClient[] | null;
  restaurants?: IRestaurant[] | null;
}

export const defaultValue: Readonly<ICooperativelocal> = {};
