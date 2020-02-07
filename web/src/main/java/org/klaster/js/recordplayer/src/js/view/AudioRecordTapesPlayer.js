import {Component} from "./Component.js";
import {RecordPlayer} from "../service";
import {AudioRecord} from "../model";

/**
 * UI element for audio record tapes player
 * @class
 * @property {AudioRecordTapesContainer} audioRecordTapesContainer - container for audio record tapes
 * @property {RecordPlayer} recordPlayer - record player
 */
export class AudioRecordTapesPlayer extends Component {
  constructor(props = {}) {
    props = _.merge(AudioRecordTapesPlayer.DEFAULT_PROPS,
                    props);
    super(props);
    this._recordPlayer = new RecordPlayer();
  }

  get audioRecordTapesContainer() {
    return this.audioRecordTapesContainer;
  }

  set audioRecordTapesContainer(newAudioRecordTapesContainer) {
    this._audioRecordTapesContainer = newAudioRecordTapesContainer;
  }

  ondrop(event) {
    event.preventDefault();
    this.pause();
    const audioRecord = AudioRecord.fromJson(event.dataTransfer.getData('text/json'));
    if (audioRecord != null) {
      if (this._audioRecordTapesContainer != null) {
        this._audioRecordTapesContainer.removeAudioRecordTape(audioRecord);
        if (this._recordPlayer.hasAudioRecord) {
          this._audioRecordTapesContainer.addAudioRecord(this._recordPlayer.audioRecord);
        }
      }
      this._recordPlayer.audioRecord = audioRecord;
      this.play();
    }
  }

  play() {
    this._recordPlayer.play();
  }

  pause() {
    this._recordPlayer.pause();
  }

  onclick(event) {

  }
}

AudioRecordTapesPlayer.DEFAULT_PROPS = {
  attributes: {
    class: 'icon radio max-height center max-width'
  }
};

export default AudioRecordTapesPlayer;