var roleUpgrader = { run : function (creep) {
    if (creep.memory.upgrading && creep.carry.energy === 0) {
        creep.memory.upgrading = false;
        creep.say('harvesting');
    };
    if (!creep.memory.upgrading && creep.carry.energy === creep.carryCapacity) {
        creep.memory.upgrading = true;
        creep.say('upgrading');
    };
    if (creep.memory.upgrading) {
        return creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE ? creep.moveTo(creep.room.controller) : null;
    } else {
        var sources = creep.room.find(FIND_SOURCES);
        return creep.harvest(sources[0]) === ERR_NOT_IN_RANGE ? creep.moveTo(sources[0]) : null;
    };
} };
module.exports = roleUpgrader;
