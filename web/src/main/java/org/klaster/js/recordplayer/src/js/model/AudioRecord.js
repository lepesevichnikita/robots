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

  equals(audioRecord) {
    return audioRecord != null && audioRecord.name === this._name;
  }
}

export default AudioRecord;
