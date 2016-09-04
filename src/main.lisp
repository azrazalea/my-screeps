(defvar role-harvester (require "role.harvester"))
(defvar role-upgrader (require "role.upgrader"))
(defvar role-builder (require "role.builder"))
(defvar roles (create
             harvester role-harvester
             upgrader role-upgrader
             builder role-builder))

(setf module.exports.loop
      (lambda ()
        (when-let (tower (chain -game (get-object-by-id "TOWER_ID")))
          (when-let (closest-damaged-structure
                     (chain tower pos (find-closest-by-range
                                       *FIND_STRUCTURES*
                                       (create filter (lambda (structure) (< structure.hits (getprop structure 'hits-max)))))))
            (tower.repair closest-damaged-structure))
          (when-let (closest-hostile (chain tower pos (find-closest-by-range *FIND_HOSTILE_CREEPS*)))
            (tower.attack closest-hostile)))

        (for-in (name (getprop -game 'creeps))
                (let ((creep (getprop (getprop -game 'creeps) name)))
                  (dolist (role '("harvester" "upgrader" "builder"))
                    (when (eql role creep.memory.role)
                      (chain (getprop roles role) (run creep))))))))
