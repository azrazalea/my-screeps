(defvar role-harvester
  (create run
          (lambda (creep)
            (if (< creep.carry.energy (getprop creep 'carry-capacity))
                (let ((sources (creep.room.find *FIND_SOURCES*)))
                  (when (eql (creep.harvest (aref sources 0)) *ERR_NOT_IN_RANGE*)
                    (chain creep (move-to (aref sources 0)))))
                (let ((targets (creep.room.find *FIND_STRUCTURES*
                                                (create filter
                                                        (lambda (structure)
                                                          (let ((type (getprop structure 'structure-type)))
                                                            (and (or (eql type *STRUCTURE_EXTENSION*)
                                                                     (eql type *STRUCTURE_SPAWN*)
                                                                     (eql type *STRUCTURE_TOWER*))
                                                                 (< structure.energy (getprop structure 'energy-capacity)))))))))
                  (when (and (> targets.length 0) (eql (creep.transfer (aref targets 0) *RESOURCE_ENERGY*) *ERR_NOT_IN_RANGE*))
                    (chain creep (move-to (aref targets 0)))))))))

(setf module.exports role-harvester)
