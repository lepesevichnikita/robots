/**
 * Stores info about audio record
 * @class
 * @property {String} name - name of audio record
 * @property {String} path - path to audio record
 */
export class AudioRecord {
  /**
   * @constructs AudioRecord
   * @param {Object} props
   * @param {String} [props.name = ''] - name of audio record
   * @param {String} [props.path = ''] - path to audio record
   */
  constructor(props = {}) {
    props = _.merge(AudioRecord.DEFAULT_PROPS,
                    props);
    this._name = props.name;
    this._path = props.path;
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

  static fromJson(json) {
    const object = JSON.parse(json);
    return this.fromObject(object);
  }

  static fromObject(object) {
    return new AudioRecord(object);
  }

  toObject() {
    return {
      name: this.name,
      path: this.path
    };
  }

  toJson() {
    return JSON.stringify(this.toObject());
  }
}

AudioRecord.DEFAULT_PROPS = {
  name: '',
  path: ''
};

export default AudioRecord;
