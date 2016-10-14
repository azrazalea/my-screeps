(ns my-screeps.spawning-pool
  (:require screeps.game
            screeps.creep
            screeps.memory
            screeps.position
            screeps.room
            screeps.spawn
            screeps.structure))

(defn create-creep-if-low [spawn creep-type creep-count limit]
  (when (< creep-count limit)
    (screeps.spawn/create-creep spawn [js/WORK js/CARRY js/MOVE] {:role creep-type})))

(defn run []
  (let [spawn  (first (screeps.game/spawns))
        room   (screeps.spawn/room spawn)
        creeps (screeps.game/creeps)
        counts (frequencies (map #(-> % screeps.creep/memory :role) creeps))]
    (mapv (fn [[creep-type creep-count]] (create-creep-if-low spawn creep-type creep-count 2)) counts)))
