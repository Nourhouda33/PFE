import { Developpeurs } from "./Developpeurs.Entity";

export class Tache{
    constructor(
        public id ?: number,
        public discription?: String,
       
        public status?: String,
        public developpeurs?: Developpeurs,
            ){}
}