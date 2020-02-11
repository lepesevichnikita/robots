function Animal(name) {
  this._name = name;
}


Animal.prototype.move = function () {
  console.log('Default move')
};

Animal.prototype.sayMyName = function () {
  console.log(`My name is ${this._name}`);
};

function Pig(name) {
  Animal.call(this,
              name);
}

Pig.prototype = Object.create(Animal.prototype);
Pig.prototype.move = function () {
  Animal.prototype.move.call(this);
};
Pig.prototype.sayMyName = function () {
  console.log("i am the pig");
  Animal.prototype.sayMyName.call(this);
};

function Bird(name) {
  Animal.call(this,
              name);
}

Bird.prototype = Object.create(Animal.prototype);
Bird.prototype.constructor = Animal;
Bird.prototype.move = function () {
  console.log('Fly');
};

console.dir(Bird);
console.dir(Pig);
const firstAnimal = new Bird('BigBird');
const secondAnimal = new Pig('RedPig');

firstAnimal.sayMyName();
secondAnimal.sayMyName();

firstAnimal.move();
secondAnimal.move();
