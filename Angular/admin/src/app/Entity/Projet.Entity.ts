import { Equipe } from "./Equipe.Entity";


export class Projet{
    constructor(
        public id ?: number,
        public logo?: String,
        public nom?:String,
        public dateDebuit?: Date,
        public dateFin?: Date,
        public discription?: String,
        public status?: String,
        public equipe?: Equipe,
       

        
    ){}
}