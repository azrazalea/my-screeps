var roleHarvester = require('role.harvester');
var roleUpgrader = require('role.upgrader');
var roleBuilder = require('role.builder');
var roles = { harvester : roleHarvester,
              upgrader : roleUpgrader,
              builder : roleBuilder
            };
module.exports.loop = function () {
    var tower = Game.getObjectById('TOWER_ID');
    if (tower) {
        var closestDamagedStructure = tower.pos.findClosestByRange(FIND_STRUCTURES, { filter : function (structure) {
            return structure.hits < structure.hitsMax;
        } });
        if (closestDamagedStructure) {
            tower.repair(closestDamagedStructure);
        };
        var closestHostile = tower.pos.findClosestByRange(FIND_HOSTILE_CREEPS);
        if (closestHostile) {
            tower.attack(closestHostile);
        };
    };
    for (var name in Game.creeps) {
        var creep = Game.creeps[name];
        for (var role = null, _js_arrvar2 = ['harvester', 'upgrader', 'builder'], _js_idx1 = 0; _js_idx1 < _js_arrvar2.length; _js_idx1 += 1) {
            role = _js_arrvar2[_js_idx1];
            if (role === creep.memory.role) {
                roles[role].run(creep);
            };
        };
    };
};
