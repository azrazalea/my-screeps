(ns my-screeps.builder
  (:require screeps.creep
            screeps.room
            screeps.structure))

(defn run [creep]
  (if (:building (screeps.creep/memory creep))
    (when (= 0 (screeps.creep/energy creep))
      (screeps.creep/memory! creep (assoc (screeps.creep/memory creep) :building false))
      (screeps.creep/say creep "harvesting"))
    (when (= (screeps.creep/energy creep) (screeps.creep/energy-capacity creep))
      (screeps.creep/memory! creep (assoc (screeps.creep/memory creep) :building true))
      (screeps.creep/say creep "building")))
  ;; This looks redundant, but it isn't due to the wonder of procedural programming
  (if (:building (screeps.creep/memory creep))
    (let [targets (.find (.-room creep) js/FIND_CONSTRUCTION_SITES)]
      (when (and (not (empty? targets)) (= (screeps.creep/build creep (aget targets 0)) js/ERR_NOT_IN_RANGE))
        (screeps.creep/move-to creep (aget targets 0))))
    (let [sources (.find (.-room creep) js/FIND_SOURCES)]
      (when (= (screeps.creep/harvest creep (aget sources 0)) js/ERR_NOT_IN_RANGE)
        (screeps.creep/move-to creep (aget sources 0))))))
