(ns my-screeps.upgrader
  (:require screeps.creep
            screeps.room
            screeps.structure))

(defn run [creep]
  (if (:upgrading (screeps.creep/memory creep))
    (when (= 0 (screeps.creep/energy creep))
      (screeps.creep/memory! creep (assoc (screeps.creep/memory creep) :upgrading false))
      (screeps.creep/say creep "harvesting"))
    (when (= (screeps.creep/energy creep) (screeps.creep/energy-capacity creep))
      (screeps.creep/memory! creep (assoc (screeps.creep/memory creep) :upgrading true))
      (screeps.creep/say creep "upgrading")))
  ;; This looks redundant, but it isn't due to the wonder of procedural programming
  (if (:upgrading (screeps.creep/memory creep))
    (when (= (screeps.creep/upgrade-controller creep (screeps.room/controller (screeps.creep/room creep))) js/ERR_NOT_IN_RANGE)
      (screeps.creep/move-to creep (screeps.room/controller (screeps.creep/room creep))))
    (let [sources (.find (.-room creep) js/FIND_SOURCES)]
      (when (= (screeps.creep/harvest creep (aget sources 0)) js/ERR_NOT_IN_RANGE)
        (screeps.creep/move-to creep (aget sources 0))))))
