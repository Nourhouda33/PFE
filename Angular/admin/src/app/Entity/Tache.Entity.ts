import { Developpeurs } from "./Developpeurs.Entity";
import { Projet } from "./Projet.Entity";

export class Tache{
    constructor(
        public id ?: number,
       public nom?: String,
       public date?:Date,
        public projet?: Projet,

            ){}
}