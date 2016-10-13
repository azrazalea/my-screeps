(ns my-screeps.harvester
  (:require screeps.creep
            screeps.room
            screeps.structure))

(defn run
  [creep]
  (if (< (screeps.creep/energy creep) (screeps.creep/energy-capacity creep))
    (let [sources (.find (.-room creep) js/FIND_SOURCES)]
      (when (= (screeps.creep/harvest creep (aget sources 0)) js/ERR_NOT_IN_RANGE)
        (screeps.creep/move-to creep (aget sources 0))))
    (let [targets (screeps.room/find (.-room creep) js/FIND_STRUCTURES (fn [structure]
                                                                        (and (or (= (screeps.structure/type structure) js/STRUCTURE_EXTENSION)
                                                                                 (= (screeps.structure/type structure) js/STRUCTURE_SPAWN)
                                                                                 (= (screeps.structure/type structure) js/STRUCTURE_TOWER))
                                                                             (< (screeps.structure/energy structure) (screeps.structure/energy-capacity structure)))))]
      (println targets)
      (when (> (.-length targets) 0)
        (println (aget targets 0))
        (println (screeps.creep/transfer creep (aget targets 0)))
        (when (= (screeps.creep/transfer creep (aget targets 0)) js/ERR_NOT_IN_RANGE)
         (screeps.creep/move-to creep (aget targets 0)))))))
