(defvar role-upgrader
  (create run (lambda (creep)
                   (when (and creep.memory.upgrading (eql creep.carry.energy 0))
                     (setf creep.memory.upgrading false)
                     (creep.say "harvesting"))
                   (when (and (not creep.memory.upgrading) (eql creep.carry.energy (getprop creep 'carry-capacity)))
                     (setf creep.memory.upgrading true)
                     (creep.say "upgrading"))
                   (if creep.memory.upgrading
                       (when (equal (chain creep (upgrade-controller creep.room.controller)) *ERR_NOT_IN_RANGE*)
                         (chain creep (move-to creep.room.controller)))
                       (let ((sources (creep.room.find *FIND_SOURCES*)))
                         (when (eql (creep.harvest (aref sources 0)) *ERR_NOT_IN_RANGE*)
                           (chain creep (move-to (aref sources 0)))))))))

(setf module.exports role-upgrader)
