package aed;

import java.util.ArrayList;
import java.util.List;

public class DictEstudiantes<K, V> {
        K libreta;
        String<> valor;

        public DictEstudiantes(K libreta, String carrera, String materia) {
            this.libreta = libreta;
            this.valor = new ParCarreraMateria(carrera,materia);
        }

        public K getLibreta() {
            return libreta;
        }

        public V getValor() {
            return valor;
        }
    }

    // Clase HashMap personalizada
    class MyHashMap<K, V> {
        private List<DictEstudiantes<K, V>>[] buckets;
        private int capacity;
        private int size;

        @SuppressWarnings("unchecked")
        public MyHashMap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.buckets = new List[capacity];
            // Inicializa cada cubeta con una lista vacía
            for (int i = 0; i < capacity; i++) {
                buckets[i] = new ArrayList<DictEstudiantes<K, V>>();
            }
        }

        private int getBucketIndex(K libreta) {
            int hashCode = libreta.hashCode();
            return Math.abs(hashCode % capacity);
        }

        public void put(K libreta, V valor) {
            int bucketIndex = getBucketIndex(libreta);
            List<DictEstudiantes<K, V>> bucket = buckets[bucketIndex];

            // Verificar si la clave ya existe en el mapa y actualizar su valor si es así
            for (DictEstudiantes<K, V> entry : bucket) {
                if (entry.getLibreta().equals(libreta)) {
                    entry.valor = valor;
                    return;
                }
            }

            // Agregar una nueva entrada si la clave no existe en el mapa
            bucket.add(new DictEstudiantes<>(libreta, valor));
            size++;
        }

        public V get(K libreta) {
            int bucketIndex = getBucketIndex(libreta);
            List<DictEstudiantes<K, V>> bucket = buckets[bucketIndex];

            for (DictEstudiantes<K, V> entry : bucket) {
                if (entry.getLibreta().equals(libreta)) {
                    return entry.getValor();
                }
            }

            return null; // Clave no encontrada
        }

        public void remove(K libreta) {
            int bucketIndex = getBucketIndex(libreta);
            List<DictEstudiantes<K, V>> bucket = buckets[bucketIndex];

            for (DictEstudiantes<K, V> entry : bucket) {
                if (entry.getLibreta().equals(libreta)) {
                    bucket.remove(entry);
                    size--;
                    return;
                }
            }
        }

        public boolean containsLibreta(K libreta) {
            int bucketIndex = getBucketIndex(libreta);
            List<DictEstudiantes<K, V>> bucket = buckets[bucketIndex];

            for (DictEstudiantes<K, V> entry : bucket) {
                if (entry.getLibreta().equals(libreta)) {
                    return true;
                }
            }

            return false;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    // Ejemplo de uso
    public class Main {
        public static void main(String[] args) {
            MyHashMap<String, Integer> myHashMap = new MyHashMap<>(10);

            myHashMap.put("clave1", 1);
            myHashMap.put("clave2", 2);
            myHashMap.put("clave3", 3);

            System.out.println("Valor para clave2: " + myHashMap.get("clave2"));

            myHashMap.remove("clave2");

            System.out.println("Tamaño del mapa: " + myHashMap.size());

            System.out.println("¿Contiene clave3? " + myHashMap.containsLibreta("clave3"));
            System.out.println("¿Contiene clave2? " + myHashMap.containsLibreta("clave2"));
        }
    }

