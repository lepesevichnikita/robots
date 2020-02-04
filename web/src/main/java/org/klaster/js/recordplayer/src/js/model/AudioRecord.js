export class AudioRecord {
  constructor(params = {name: "New record", path: ""}) {
    this._name = params.name;
    this._path = params.path;
  }

  get name() {
    return this._name;
  }

  get path() {
    return this._path;
  }
}

export default AudioRecord;
