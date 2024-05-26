import { Tache } from "./Tache.Entity";

export class SousTache{
    constructor(
        public id ?: number,
       public description?: String,
       public commentaire?:String,
        public fichier?: String,
        public image?:String,
        public checkList?:String[],
        public tache?:Tache,

            ){}
}