(ns my-screeps.core
  (:require screeps.game
            screeps.creep
            screeps.memory
            screeps.position
            screeps.room
            screeps.spawn
            screeps.structure
            [my-screeps.harvester :as harvester]
            [my-screeps.upgrader :as upgrader]
            [my-screeps.builder :as builder]))

(enable-console-print!)

(defn main-loop
  []
  (when-let [tower (screeps.game/object "TOWER_ID")]
    (when-let [closest-damaged-structure
               (.findClosestByRange js/FIND_STRUCTURES {
                                                        :filter (fn [structure] (< (.-hits structure) (.-hitsMax structure)))})]
      (.repair tower closest-damaged-structure))
    (when-let [closest-hostile (.findClosestByRange js/FIND_HOSTILE_CREEPS)]
      (.attack tower closest-hostile)))

  (doseq [creep (screeps.game/creeps)]
    (case (:role (screeps.creep/memory creep))
      "harvester" (harvester/run creep)
      "upgrader" (upgrader/run creep)
      "builder" (builder/run creep)
      nil)))

(set! (.-exports js/module) #js {:loop main-loop})
