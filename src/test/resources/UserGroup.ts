import { IUser } from './User';
export class UserGroup {
    public administrator: IUser;
    public users: Array<IUser>;
}