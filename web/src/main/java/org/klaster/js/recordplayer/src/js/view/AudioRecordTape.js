import {Component} from "./Component.js";

/**
 * Builds HTMLElement for audio record tape
 * @class
 * @property {AudioRecord} audioRecord - data about audio record
 */
export class AudioRecordTape extends Component {
  constructor(props) {
    props = _.merge(AudioRecordTape.DEFAULT_PROPS, props);
    super(props);
    this._audioRecord = props.audioRecord;
  }

  onclick(event) {
    console.log('TAPE');
  }
}

AudioRecordTape.DEFAULT_PROPS = {
  attributes: {
    class: 'icon cassette-tape narrow max-height center',
    draggable: true
  }
};

export default AudioRecordTape;