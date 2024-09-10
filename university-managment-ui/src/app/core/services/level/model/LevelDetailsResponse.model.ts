export interface LevelDetailsResponseModel {
  id?: number;
  name?: string;
  description?: string;
  department?: string;
  specialityWithTrack?: string;
}

export class FilterLevelParamModel {
  [key: string]: string | undefined;

  constructor(specialityLike?: string,
              trackLike?: string,
              departmentLike?: string) {
  }
}

