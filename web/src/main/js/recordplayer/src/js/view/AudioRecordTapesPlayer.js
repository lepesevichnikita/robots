import {Component} from "./Component.js";
import {RecordPlayer} from "../service";
import {AudioRecord} from "../model";

/**
 * UI element for audio record tapes player
 * @class
 * @property { AudioRecordTapesContainer } audioRecordTapesContainer - container for audio record tapes
 * @property { RecordPlayer } recordPlayer - record player
 */
export class AudioRecordTapesPlayer extends Component {
  constructor(props = {}) {
    super(props);
    this._recordPlayer = new RecordPlayer({onended: this.onended.bind(this)});
    this.ondrop = this.ondrop.bind(this);
  }

  onended(event) {
    event.preventDefault();
    this._changeImageToRadioOff();
    this._returnCurrentAudioRecordFromPlayerIntoContainer();
  }

  prepareComponentToRender() {
    this.setAttributes(AudioRecordTapesPlayer.DEFUALT_ATTRIBUTES);
    this.setEventListeners({drop: this.ondrop.bind(this)});
    super.prepareComponentToRender();
  }

  set audioRecordTapesContainer(newAudioRecordTapesContainer) {
    this._audioRecordTapesContainer = newAudioRecordTapesContainer;
  }

  ondrop(event) {
    event.preventDefault();
    this.pause();
    const audioRecordAsJson = event.dataTransfer.getData('text/json');
    if (audioRecordAsJson) {
      const audioRecord = AudioRecord.fromJson(audioRecordAsJson);
      this._returnCurrentAudioRecordFromPlayerIntoContainer();
      this._removeAudioRecordTapeFromContainer(audioRecord);
      this._recordPlayer.audioRecord = audioRecord;
      this.play();
    }
  }

  play() {
    this.element.classList.remove('radio-off');
    this.element.classList.add('radio');
    this._recordPlayer.play();
  }

  pause() {
    this._changeImageToRadioOff();
    this._recordPlayer.pause();
  }

  /**
   * Removes audio record tape from container by passed audio record
   * @param { AudioRecord } audioRecord - removed audio record
   * @private
   */
  _removeAudioRecordTapeFromContainer(audioRecord) {
    if (this._audioRecordTapesContainer) {
      this._audioRecordTapesContainer.removeAudioRecordTape(audioRecord);
    }
  }

  /**
   * Eject current audio record from player and adds to audio record tapes container
   * @private
   */
  _returnCurrentAudioRecordFromPlayerIntoContainer() {
    if (this._recordPlayer.hasAudioRecord) {
      this._audioRecordTapesContainer.addAudioRecord(
          this._recordPlayer.audioRecord);
    }
  }

  _changeImageToRadioOff() {
    this.element.classList.add('radio-off');
    this.element.classList.remove('radio');
  }
}

AudioRecordTapesPlayer.DEFUALT_ATTRIBUTES = {class: 'icon max-height center max-width record-player radio-off'};

export default AudioRecordTapesPlayer;