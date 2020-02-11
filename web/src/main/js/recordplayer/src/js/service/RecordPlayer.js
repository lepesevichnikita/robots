/**
 * Provide functionality to play audio records
 * @class
 * @property {Audio} audioObject - audio object to play records
 * @property {AudioRecord} audioRecord - current played audio record
 * @property {Boolean} hasCurrentAudioPlay - true, if current audio record is not null;
 */

export class RecordPlayer {
  /**
   * @constructs RecordPlayer
   * @param {Object} props
   * @param {Audio} [props.audioObject=new Audio()] - audio object to play records
   * @param  {AudioRecord} [props.audioRecord=null] - current played audio record
   */
  constructor(props = {}) {
    props = _.merge(RecordPlayer.DEFAULT_PROPS,
                    props);
    this._audioRecord = props.audioRecord;
    this._audioObject = props.audioObject;
  }

  get audioRecord() {
    return this._audioRecord;
  }

  set audioRecord(value) {
    this._audioRecord = value;
  }

  get hasAudioRecord() {
    return this._audioRecord != null;
  }

  /**
   * Plays current audio record
   */
  play() {
    if (this.hasAudioRecord) {
      this._audioObject.src = this._audioRecord.path;
      this._audioObject.play()
    }
  }

  /**
   * Stops current audio record
   */
  stop() {
    this._audioObject.stop();
  }

  /**
   * Pauses current audio record
   */
  pause() {
    this._audioObject.pause();
  }
}

RecordPlayer.DEFAULT_PROPS = {
  audioRecord: null,
  audioObject: new Audio()
};


export default RecordPlayer;