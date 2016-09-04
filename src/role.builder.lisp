(defvar role-builder
  (create run
          (lambda (creep)
            (when (and creep.memory.building (eql creep.carry.energy 0))
              (setf creep.memory.building false)
              (creep.say "harvesting"))
            (when (and (not creep.memory.building) (eql creep.carry.energy (getprop creep 'carry-capacity)))
              (setf creep.memory.building true)
              (creep.say "building"))
            (if creep.memory.building
                (let ((targets (creep.room.find *FIND_CONSTRUCTION_SITES*)))
                  (when (and (> targets.length 0) (eql (creep.build (aref targets 0)) *ERR_NOT_IN_RANGE*))
                    (chain creep (move-to (aref targets 0)))))
                (let ((sources (creep.room.find *FIND_SOURCES*)))
                  (when (and (> sources.length 0) (eql (creep.harvest (aref sources 0)) *ERR_NOT_IN_RANGE*))
                    (chain creep (move-to (aref sources 0)))))))))

(setf module.exports role-builder)
