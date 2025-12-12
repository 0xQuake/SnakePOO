# SnakePOO
Proyecto Final de la Materia Programación Orientada a Objetos
# Juego de la Serpiente - Java MVC con Patrones de Diseño

Un clon del juego Snake construido con Java Vanilla y Swing, implementando arquitectura MVC estricta y múltiples patrones de diseño.

## Cómo Ejecutar

### Compilar y Ejecutar
```bash
# Compilar todos los archivos Java
javac -d out src/snake/**/*.java src/snake/*.java

# Ejecutar el juego
java -cp out snake.Main
```

### Inicio Rápido
```bash
./run.sh
```

## Controles

| Tecla | Acción |
|-------|--------|
| Flechas / WASD | Mover serpiente |
| ESPACIO | Pausar / Reanudar |
| 1 | Velocidad lenta |
| 2 | Velocidad normal |
| 3 | Velocidad rápida |
| ENTER | Reiniciar (después de fin del juego) |

## Estructura del Proyecto

```
src/snake/
├── Main.java                          # Punto de entrada
├── config/
│   └── GameConfig.java                # Singleton - Configuración del juego
├── model/
│   ├── Direction.java                 # Enum de direcciones de movimiento
│   ├── GameState.java                 # Enum de estado del juego
│   ├── Position.java                  # Clase de posición en cuadrícula
│   ├── GameModel.java                 # Lógica principal del juego
│   ├── composite/                     # Patrón Composite
│   │   ├── SnakeComponent.java        # Interfaz de componente
│   │   ├── SnakeHead.java             # Hoja - Cabeza de serpiente
│   │   ├── SnakeSegment.java          # Hoja - Segmento del cuerpo
│   │   └── SnakeBodyComposite.java    # Compuesto - Serpiente completa
│   ├── factory/                       # Patrón Factory Method
│   │   ├── Food.java                  # Producto - Elemento de comida
│   │   ├── FoodFactory.java           # Interfaz creadora
│   │   ├── NormalFoodFactory.java     # Creador concreto
│   │   └── BonusFoodFactory.java      # Creador concreto
│   ├── strategy/                      # Patrón Strategy
│   │   ├── MovementStrategy.java      # Interfaz de estrategia
│   │   ├── NormalSpeed.java           # Estrategia concreta
│   │   ├── FastSpeed.java             # Estrategia concreta
│   │   └── SlowSpeed.java             # Estrategia concreta
│   └── observer/                      # Patrón Observer
│       ├── GameObserver.java          # Interfaz observador
│       └── GameSubject.java           # Interfaz sujeto
├── view/
│   ├── GameFrame.java                 # Ventana principal
│   ├── GamePanel.java                 # Renderizado del juego
│   └── ScorePanel.java                # Visualización de puntuación
└── controller/
    └── GameController.java            # Entrada y bucle del juego
```

## Patrones de Diseño Aplicados

### Patrones Creacionales

**Factory Method**
- Interfaz `FoodFactory` con implementaciones `NormalFoodFactory` y `BonusFoodFactory`
- Desacopla la creación de comida de la lógica del juego, permitiendo diferentes tipos de comida con propiedades únicas

**Singleton**
- `GameConfig` asegura que existe una sola instancia de configuración del juego
- Proporciona acceso global a constantes del juego (tamaño de cuadrícula, tamaño de celda, etc.)

### Patrones Estructurales

**Composite**
- Interfaz `SnakeComponent` con `SnakeHead`, `SnakeSegment` (hojas) y `SnakeBodyComposite` (compuesto)
- Trata partes individuales de la serpiente y todo el cuerpo de manera uniforme para operaciones como movimiento y detección de colisiones

### Patrones de Comportamiento

**Observer**
- Interfaces `GameSubject` y `GameObserver`
- Componentes de vista (`GamePanel`, `ScorePanel`) se suscriben a cambios del modelo
- Asegura desacoplamiento completo entre las capas Modelo y Vista

**Strategy**
- Interfaz `MovementStrategy` con implementaciones `SlowSpeed`, `NormalSpeed`, `FastSpeed`
- Permite cambiar la dificultad/velocidad del juego en tiempo de ejecución sin modificar la lógica del juego

## Arquitectura MVC

### Modelo (Sin dependencias de UI)
- Contiene toda la lógica del juego: posiciones, movimiento, colisiones, puntuación
- Completamente independiente de Swing/AWT
- Notifica a los observadores cuando cambia el estado

### Vista (Solo Swing)
- Renderiza el estado del juego usando `paintComponent`
- Se suscribe a actualizaciones del modelo vía patrón Observer
- Sin lógica de juego directa

### Controlador
- Gestiona el bucle del juego vía Timer de Swing
- Maneja entrada del teclado (KeyListener)
- Orquesta comunicación Modelo ↔ Vista

## Principios SOLID

- **S**ingle Responsibility: Cada clase tiene un propósito claro
- **O**pen/Closed: Los patrones Factory y Strategy permiten extensión sin modificación
- **L**iskov Substitution: Todas las implementaciones son intercambiables con sus interfaces
- **I**nterface Segregation: Interfaces pequeñas y enfocadas (GameObserver, FoodFactory, etc.)
- **D**ependency Inversion: Módulos de alto nivel dependen de abstracciones, no de concreciones
