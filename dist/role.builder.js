var roleBuilder = { run : function (creep) {
    if (creep.memory.building && creep.carry.energy === 0) {
        creep.memory.building = false;
        creep.say('harvesting');
    };
    if (!creep.memory.building && creep.carry.energy === creep.carryCapacity) {
        creep.memory.building = true;
        creep.say('building');
    };
    if (creep.memory.building) {
        var targets = creep.room.find(FIND_CONSTRUCTION_SITES);
        return targets.length > 0 && creep.build(targets[0]) === ERR_NOT_IN_RANGE ? creep.moveTo(targets[0]) : null;
    } else {
        var sources = creep.room.find(FIND_SOURCES);
        return sources.length > 0 && creep.harvest(sources[0]) === ERR_NOT_IN_RANGE ? creep.moveTo(sources[0]) : null;
    };
} };
module.exports = roleBuilder;
