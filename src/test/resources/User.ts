import { IModel } from './IModel';

export interface IUser extends IModel {

    name: string;
}

export class User implements IUser {
    
    public autoId: number;
    public name: string;
}