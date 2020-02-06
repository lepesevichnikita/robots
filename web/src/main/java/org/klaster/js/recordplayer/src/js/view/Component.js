/**
 * Wrapped HTMLElement
 * @class
 * @property {HTMLElement} element - HTMLElement, that can be rendered in parent
 */
export class Component {
  /**
   * @constructs Component
   * @param {Object} props
   * @param {String} props.elementName - properties
   * @param {Object} props.attributes - attributes for HTMLElement
   * @param {Object} props.eventListeners - event listeners for HTMLElement
   */
  constructor(props) {
    props = {...Component.DEFAULT_PROPS, ...props};
    this._elementName = props.elementName;
    this._attributes = props.attributes;
    this._eventListeners = props.eventListeners;
    this._createElement();
    this._setAttributes();
    this._setEventHandlers();
  }

  get element() {
    return this._element;
  }

  _createElement() {
    this._element = document.createElement(this._elementName);
  }

  _setAttributes() {
    if (this._attributes != null) {
      Object.keys(this._attributes).forEach(key => {
        const value = this._attributes[key];
        this._element.setAttribute(key, value);
      });
    }
  }

  _setEventHandlers() {
    if (this._eventListeners != null) {
      Object.keys(this._eventListeners).forEach(eventName => {
        const eventListener = this._eventListeners[eventName];
        this._element.addEventListener(eventName, eventListener);
      })
    }
  }

  appendToChildren(node) {
    node.appendChild(this._element);
  }

  renderAt(node) {
    node.replaceWith(this._element)
  }

  remove() {
    this._element.parentElement.removeChild(this._element);
  }
}

Component.DEFAULT_PROPS = {
  elementName: 'div',
  attributes: {},
  eventListeners: {}
};

export default Component;