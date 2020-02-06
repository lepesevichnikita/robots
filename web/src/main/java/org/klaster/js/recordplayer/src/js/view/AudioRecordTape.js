import {Component} from "./Component.js";

/**
 * Builds HTMLElement for audio record tape
 * @class
 * @property {AudioRecord} audioRecord - data about audio record
 */
export class AudioRecordTape extends Component {
  constructor(props) {
    props = {
      ...props,
      attributes: {...AudioRecordTape.DEFAULT_ATTRIBUTES, ...props.attributes},
    };
    super(props);
    this._audioRecord = props.audioRecord;
  }
}

AudioRecordTape.DEFAULT_ATTRIBUTES = {
  class: 'icon cassette-tape narrow max-height center',
  draggable: true
};

export default AudioRecordTape;