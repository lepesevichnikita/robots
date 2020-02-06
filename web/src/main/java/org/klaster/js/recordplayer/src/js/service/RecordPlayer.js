/**
 * Provide functionality to play audio records
 * @class
 * @property {Audio} audioObject - audio object to play records
 * @property {AudioRecord} currentAudioRecord - current played audio record
 * @property {Boolean} hasCurrentAudioPlay - true, if current audio record is not null;
 */

export class RecordPlayer {
  /**
   * @constructs RecordPlayer
   * @param {Object} props
   * @param {Audio} [props.audioObject=new Audio()] - audio object to play records
   * @param  {AudioRecord} [props.currentAudioRecord=null] - current played audio record
   */
  constructor(props = {currentAudioRecord: null, audioObject: new Audio()}) {
    this._currentAudioRecord = props.currentAudioRecord;
    this._audioObject = props.audioObject
  }

  get currentAudioRecord() {
    return this._currentAudioRecord;
  }

  set currentAudioRecord(value) {
    this._currentAudioRecord = value;
  }

  get audioObject() {
    return this._audioObject;
  }

  get hasCurrentAudioRecord() {
    return this._currentAudioRecord != null;
  }

  play() {
    if (this.hasCurrentAudioRecord) {
      this._audioObject.src = this._currentAudioRecord.path;
      this._audioObject.play()
    }
  }

  stop() {
    this._audioObject.stop();
  }
}

export default RecordPlayer;