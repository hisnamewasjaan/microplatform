export interface IAd {
  id: string;
  name: string;
  description: string;
  price: number;
}

export class Ad {
  // constructor()
  constructor(
      public id?: string,
      public name?: string,
      public description?: string,
      public price?: number
  ) {
  }
}


export const ADS: Ad[] = [
  new Ad('1000', 'Sous Vide', 'Blah blah', 666.0),
  new Ad('2000', 'Cykel', '', 999.0)
];
