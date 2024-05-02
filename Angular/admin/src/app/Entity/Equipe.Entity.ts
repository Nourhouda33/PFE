import { Developpeurs } from "./Developpeurs.Entity";

export class Equipe{
    constructor(
        public id ?: number,
        public nom?:String,
        
        public discription?: String,
        public developpeurs?: Developpeurs,
        
    ){}
}