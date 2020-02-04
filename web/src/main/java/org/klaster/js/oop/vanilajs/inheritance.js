function Animal(name) {
  this._name = name;
}

Animal.prototype.move = function () {
  console.log('Default move')
};

Pig.prototype = new Animal();
Pig.prototype.constructor = Animal;

Pig.prototype.move = function () {
  Animal.prototype.move.call(this);
};

Bird.prototype = new Animal();
Bird.prototype.constructor = Animal;

Bird.prototype.move = function () {
  console.log('Fly');
};
