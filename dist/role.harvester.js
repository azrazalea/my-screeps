var roleHarvester = { run : function (creep) {
    if (creep.carry.energy < creep.carryCapacity) {
        var sources = creep.room.find(FIND_SOURCES);
        return creep.harvest(sources[0]) === ERR_NOT_IN_RANGE ? creep.moveTo(sources[0]) : null;
    } else {
        var targets = creep.room.find(FIND_STRUCTURES, { filter : function (structure) {
            var type = structure.structureType;
            return (type === STRUCTURE_EXTENSION || type === STRUCTURE_SPAWN || type === STRUCTURE_TOWER) && structure.energy < structure.energyCapacity;
        } });
        return targets.length > 0 && creep.transfer(targets[0], RESOURCE_ENERGY) === ERR_NOT_IN_RANGE ? creep.moveTo(targets[0]) : null;
    };
} };
module.exports = roleHarvester;
