# Resolución del ejercicio: Dependencias funcionales, claves, 3NF y BCNF

## 1. Identificación de los atributos

| Símbolo | Atributo | Significado |
|---|---|---|
| NC | Nombre Calle | Nombre de la calle. |
| NRO | Número Calle | Número que identifica la calle. |
| IP | Identificación Plano | Identificador del plano de construcción de la calle. |
| EC | Empresa Constructora | Empresa que diseñó el plano y que también construye hospitales y canchas. |
| NI | Nombre Intendente | Nombre del intendente durante cuyo ejercicio se eligió el nombre de la calle. |
| AI | Año de inauguración | Año en que se inauguró la calle. |
| HO | Hospital | Identificación del hospital. |
| CF | Cancha Fútbol | Identificación de la cancha de fútbol. |
| ING | Ingeniero | Ingeniero responsable de la construcción de un hospital o cancha. |

El esquema universal inicial es:

\[
R(NC,NRO,IP,EC,NI,AI,HO,CF,ING)
\]

**Supuestos de interpretación:** HO y CF identifican individualmente a cada hospital y cancha, respectivamente, e ING representa al ingeniero responsable. Cada plano tiene una sola empresa diseñadora; cada hospital o cancha tiene un único ingeniero responsable; y cada ingeniero trabaja para una sola empresa.

Una dependencia funcional \(X \rightarrow Y\) significa que el valor de \(X\) determina de manera única el valor de \(Y\).

---

# a) Deducir las dependencias funcionales

## A. Dependencias relacionadas con las calles

El número de calle determina el nombre, el identificador del plano y la empresa constructora:

\[
NRO \rightarrow NC
\]
\[
NRO \rightarrow IP
\]
\[
NRO \rightarrow EC
\]

De forma agrupada:

\[
NRO \rightarrow NC,IP,EC
\]

El nombre de la calle determina su número, el plano y la empresa constructora:

\[
NC \rightarrow NRO,IP,EC
\]

El nombre de la calle determina el nombre del intendente durante cuyo ejercicio se eligió:

\[
NC \rightarrow NI
\]

El enunciado dice que el nombre, el número y el intendente determinan el año de inauguración:

\[
NC,NRO,NI \rightarrow AI
\]

Pero, como \(NRO \rightarrow NC\) y \(NC \rightarrow NI\), basta con conocer NRO para determinar esos atributos. Por ello, la dependencia se reduce a:

\[
NRO \rightarrow AI
\]

También se cumple \(NC \rightarrow AI\), por transitividad a través de NRO.

## B. Dependencias relacionadas con los planos

Un mismo identificador de plano puede corresponder a distintas calles, pero cada plano es diseñado por una única empresa:

\[
IP \rightarrow EC
\]

No se cumple \(IP \rightarrow NC\) ni \(IP \rightarrow NRO\), porque un mismo plano puede describir distintas calles.

## C. Dependencias relacionadas con hospitales y canchas

Cada hospital tiene un único ingeniero responsable:

\[
HO \rightarrow ING
\]

Cada cancha de fútbol tiene un único ingeniero responsable:

\[
CF \rightarrow ING
\]

Cada ingeniero trabaja para una única empresa constructora:

\[
ING \rightarrow EC
\]

## Resumen de las dependencias funcionales iniciales

| N.º | Dependencia funcional | Justificación |
|---|---|---|
| 1 | \(NRO \rightarrow NC\) | El número identifica el nombre de la calle. |
| 2 | \(NRO \rightarrow IP\) | El número determina el plano. |
| 3 | \(NRO \rightarrow EC\) | El número determina la empresa constructora. |
| 4 | \(NC \rightarrow NRO\) | El nombre identifica el número de calle. |
| 5 | \(NC \rightarrow IP\) | El nombre determina el plano. |
| 6 | \(NC \rightarrow EC\) | El nombre determina la empresa constructora. |
| 7 | \(NC \rightarrow NI\) | El nombre determina el intendente. |
| 8 | \(NC,NRO,NI \rightarrow AI\) | Los tres atributos determinan el año de inauguración. |
| 9 | \(IP \rightarrow EC\) | Cada plano tiene una única empresa diseñadora. |
| 10 | \(HO \rightarrow ING\) | Cada hospital tiene un ingeniero responsable. |
| 11 | \(CF \rightarrow ING\) | Cada cancha tiene un ingeniero responsable. |
| 12 | \(ING \rightarrow EC\) | Cada ingeniero trabaja para una única empresa. |

Las dependencias redundantes se eliminarán al calcular el cubrimiento minimal.

---

# b) Hallar todas las claves y justificarlas

El esquema universal es:

\[
R(NC,NRO,IP,EC,NI,AI,HO,CF,ING)
\]

## Paso 1. Identificar los atributos que deben estar en toda clave

HO y CF no aparecen en el lado derecho de ninguna dependencia funcional. Por tanto, no pueden determinarse a partir de otros atributos y **toda clave candidata debe incluir HO y CF**.

## Paso 2. Identificar los atributos que determinan los datos de las calles

Tenemos:

\[
NRO \rightarrow NC,IP,EC
\]

Además:

\[
NC \rightarrow NRO,IP,EC,NI
\]

Como \(NRO \rightarrow AI\), el número de calle permite obtener NC, IP, EC, NI y AI. Del mismo modo, NC permite obtener esos atributos porque determina NRO.

## Paso 3. Determinar el ingeniero y la empresa

Se cumple:

\[
HO \rightarrow ING
\]
\[
CF \rightarrow ING
\]
\[
ING \rightarrow EC
\]

Por lo tanto, HO o CF permiten obtener ING y, mediante ING, también EC. Sin embargo, no permiten obtener los atributos de la calle ni determinar HO o CF a partir de los demás atributos.

## Paso 4. Calcular el cierre de \(\{NRO,HO,CF\}\)

Sea:

\[
K_1=\{NRO,HO,CF\}
\]

Su cierre se calcula así:

1. NRO permite obtener NC, IP, EC y AI.
2. NC permite obtener NI.
3. HO permite obtener ING.
4. CF también permite obtener ING.

Por tanto:

\[
K_1^+=\{NC,NRO,IP,EC,NI,AI,HO,CF,ING\}
\]

El cierre contiene todos los atributos del esquema, así que \(K_1\) es una superclave. Además, es mínima porque ninguno de sus tres atributos puede eliminarse: HO y CF son obligatorios, y NRO es necesario para determinar los datos de las calles.

Por tanto:

\[
\boxed{K_1=\{NRO,HO,CF\}}
\]

## Paso 5. Encontrar la otra clave candidata

Como \(NC \rightarrow NRO\), podemos reemplazar NRO por NC sin perder la capacidad de determinar todos los atributos.

Así:

\[
\boxed{K_2=\{NC,HO,CF\}}
\]

También es mínima: HO y CF son obligatorios, y NC es necesario para determinar los atributos de la calle.

**Conclusión del inciso b:** las claves candidatas del esquema universal son exactamente:

\[
\boxed{\{NRO,HO,CF\}}
\]
\[
\boxed{\{NC,HO,CF\}}
\]

---

# c) Hallar un cubrimiento minimal

Un cubrimiento minimal es un conjunto equivalente de dependencias funcionales que cumple estas condiciones:

1. Cada dependencia tiene un solo atributo en el lado derecho.
2. No hay atributos innecesarios en el lado izquierdo.
3. No hay dependencias redundantes.

## Paso 1. Separar los atributos del lado derecho

Por ejemplo:

\[
NRO \rightarrow NC,IP,EC
\]

se separa en:

\[
NRO \rightarrow NC
\]
\[
NRO \rightarrow IP
\]
\[
NRO \rightarrow EC
\]

Se hace lo mismo con las demás dependencias.

## Paso 2. Eliminar atributos innecesarios del lado izquierdo

La dependencia original:

\[
NC,NRO,NI \rightarrow AI
\]

puede reducirse a:

\[
NRO \rightarrow AI
\]

porque \(NRO \rightarrow NC\) y \(NC \rightarrow NI\). Conocer NRO basta para obtener NC y NI, de modo que no hacen falta en el lado izquierdo.

## Paso 3. Eliminar dependencias redundantes

Se conserva:

\[
NRO \rightarrow NC
\]
\[
NRO \rightarrow IP
\]
\[
NRO \rightarrow AI
\]
\[
NC \rightarrow NRO
\]
\[
NC \rightarrow NI
\]
\[
IP \rightarrow EC
\]
\[
HO \rightarrow ING
\]
\[
CF \rightarrow ING
\]
\[
ING \rightarrow EC
\]

Se pueden eliminar las siguientes dependencias redundantes:

- \(NRO \rightarrow EC\), porque se deduce de \(NRO \rightarrow IP\) e \(IP \rightarrow EC\).
- \(NC \rightarrow IP\), porque se deduce de \(NC \rightarrow NRO\) y \(NRO \rightarrow IP\).
- \(NC \rightarrow EC\), porque se deduce a través de NRO e IP.
- \(NC \rightarrow AI\), porque se deduce de \(NC \rightarrow NRO\) y \(NRO \rightarrow AI\).

## Cubrimiento minimal resultante

\[
\boxed{
F_m=
\left\{
\begin{array}{l}
NRO \rightarrow NC\\
NRO \rightarrow IP\\
NRO \rightarrow AI\\
NC \rightarrow NRO\\
NC \rightarrow NI\\
IP \rightarrow EC\\
HO \rightarrow ING\\
CF \rightarrow ING\\
ING \rightarrow EC
\end{array}
\right\}
}
\]

| N.º | Dependencia funcional minimal |
|---|---|
| 1 | \(NRO \rightarrow NC\) |
| 2 | \(NRO \rightarrow IP\) |
| 3 | \(NRO \rightarrow AI\) |
| 4 | \(NC \rightarrow NRO\) |
| 5 | \(NC \rightarrow NI\) |
| 6 | \(IP \rightarrow EC\) |
| 7 | \(HO \rightarrow ING\) |
| 8 | \(CF \rightarrow ING\) |
| 9 | \(ING \rightarrow EC\) |

Este conjunto es minimal porque cada dependencia tiene un único atributo a la derecha, no tiene atributos innecesarios a la izquierda y no contiene dependencias redundantes. El cubrimiento minimal no es necesariamente único.

---

# d) Llevar a 3NF con join sin pérdida y preservando dependencias

La tercera forma normal (3NF) busca reducir redundancias y anomalías de actualización, inserción y eliminación, preservando las dependencias funcionales.

Utilizaremos el cubrimiento minimal anterior y agruparemos las dependencias que comparten el mismo determinante.

## Paso 1. Construir las relaciones

De las dependencias:

\[
NRO \rightarrow NC,\quad NRO \rightarrow IP,\quad NRO \rightarrow AI
\]

obtenemos:

\[
\boxed{R_1(NRO,NC,IP,AI)}
\]

De:

\[
NC \rightarrow NRO,\quad NC \rightarrow NI
\]

obtenemos:

\[
\boxed{R_2(NC,NRO,NI)}
\]

De:

\[
IP \rightarrow EC
\]

obtenemos:

\[
\boxed{R_3(IP,EC)}
\]

De:

\[
HO \rightarrow ING
\]

obtenemos:

\[
\boxed{R_4(HO,ING)}
\]

De:

\[
CF \rightarrow ING
\]

obtenemos:

\[
\boxed{R_5(CF,ING)}
\]

De:

\[
ING \rightarrow EC
\]

obtenemos:

\[
\boxed{R_6(ING,EC)}
\]

## Paso 2. Añadir una relación que contenga una clave candidata

Las relaciones anteriores no contienen una clave completa del esquema universal. Las claves candidatas del esquema original son:

\[
\{NRO,HO,CF\},\quad \{NC,HO,CF\}
\]

Para garantizar el join sin pérdida mediante el algoritmo de síntesis de 3NF, añadimos una relación que contenga una de esas claves:

\[
\boxed{R_7(NRO,HO,CF)}
\]

Esta relación se agrega para satisfacer la condición formal del algoritmo; no introduce una nueva dependencia funcional no trivial.

## Descomposición final en 3NF

\[
\boxed{R_1(NRO,NC,IP,AI)}
\]
\[
\boxed{R_2(NC,NRO,NI)}
\]
\[
\boxed{R_3(IP,EC)}
\]
\[
\boxed{R_4(HO,ING)}
\]
\[
\boxed{R_5(CF,ING)}
\]
\[
\boxed{R_6(ING,EC)}
\]
\[
\boxed{R_7(NRO,HO,CF)}
\]

## Paso 3. Dependencias funcionales proyectadas y claves

### Relación \(R_1(NRO,NC,IP,AI)\)

Dependencias proyectadas:

\[
NRO \rightarrow NC,IP,AI
\]
\[
NC \rightarrow NRO,IP,AI
\]

Claves candidatas:

\[
\boxed{\{NRO\},\{NC\}}
\]

NC y NRO determinan todos los atributos de esta relación.

### Relación \(R_2(NC,NRO,NI)\)

Dependencias proyectadas:

\[
NC \rightarrow NRO,NI
\]
\[
NRO \rightarrow NC,NI
\]

Claves candidatas:

\[
\boxed{\{NC\},\{NRO\}}
\]

### Relación \(R_3(IP,EC)\)

Dependencia proyectada:

\[
IP \rightarrow EC
\]

Clave candidata:

\[
\boxed{\{IP\}}
\]

### Relación \(R_4(HO,ING)\)

Dependencia proyectada:

\[
HO \rightarrow ING
\]

Clave candidata:

\[
\boxed{\{HO\}}
\]

### Relación \(R_5(CF,ING)\)

Dependencia proyectada:

\[
CF \rightarrow ING
\]

Clave candidata:

\[
\boxed{\{CF\}}
\]

### Relación \(R_6(ING,EC)\)

Dependencia proyectada:

\[
ING \rightarrow EC
\]

Clave candidata:

\[
\boxed{\{ING\}}
\]

### Relación \(R_7(NRO,HO,CF)\)

No hay dependencias funcionales no triviales proyectadas sobre estos tres atributos, según las dependencias dadas.

Clave candidata:

\[
\boxed{\{NRO,HO,CF\}}
\]

Los tres atributos son necesarios para formar una clave de esta relación.

## Paso 4. Verificar la preservación de dependencias

| Dependencia funcional | Relación donde se preserva |
|---|---|
| \(NRO \rightarrow NC\) | \(R_1\) |
| \(NRO \rightarrow IP\) | \(R_1\) |
| \(NRO \rightarrow AI\) | \(R_1\) |
| \(NC \rightarrow NRO\) | \(R_2\) |
| \(NC \rightarrow NI\) | \(R_2\) |
| \(IP \rightarrow EC\) | \(R_3\) |
| \(HO \rightarrow ING\) | \(R_4\) |
| \(CF \rightarrow ING\) | \(R_5\) |
| \(ING \rightarrow EC\) | \(R_6\) |

Todas las dependencias del cubrimiento minimal se conservan en alguna relación:

\[
\boxed{\text{Se preservan las dependencias funcionales}}
\]

## Paso 5. Verificar el join sin pérdida

El algoritmo de síntesis de 3NF garantiza el join sin pérdida si se construyen las relaciones a partir de un cubrimiento minimal y se incluye una relación que contenga una clave candidata del esquema original, cuando ninguna de las relaciones construidas la contiene.

En este caso, \(R_7(NRO,HO,CF)\) contiene la clave candidata \(\{NRO,HO,CF\}\).

Por tanto:

\[
\boxed{\text{La descomposición tiene join sin pérdida}}
\]

Esto significa que la unión natural de las relaciones permite reconstruir la relación original sin producir tuplas espurias, bajo las dependencias funcionales establecidas.

---

# e) Llevar a BCNF con join sin pérdida

La forma normal de Boyce-Codd (BCNF) es más estricta que 3NF.

Una relación está en BCNF si, para toda dependencia funcional no trivial:

\[
X \rightarrow Y
\]

el determinante \(X\) es una superclave de esa relación.

## Paso 1. Revisar las relaciones de 3NF

### Relación \(R_1(NRO,NC,IP,AI)\)

Dependencias:

\[
NRO \rightarrow NC,IP,AI
\]
\[
NC \rightarrow NRO,IP,AI
\]

Claves candidatas:

\[
\{NRO\},\{NC\}
\]

Ambos determinantes son claves candidatas. Por tanto:

\[
\boxed{R_1\text{ está en BCNF}}
\]

### Relación \(R_2(NC,NRO,NI)\)

Dependencias:

\[
NC \rightarrow NRO,NI
\]
\[
NRO \rightarrow NC,NI
\]

Claves candidatas:

\[
\{NC\},\{NRO\}
\]

Ambos determinantes son claves candidatas. Por tanto:

\[
\boxed{R_2\text{ está en BCNF}}
\]

### Relación \(R_3(IP,EC)\)

Dependencia:

\[
IP \rightarrow EC
\]

Clave candidata: \(\{IP\}\).

Por tanto:

\[
\boxed{R_3\text{ está en BCNF}}
\]

### Relación \(R_4(HO,ING)\)

Dependencia:

\[
HO \rightarrow ING
\]

Clave candidata: \(\{HO\}\).

Por tanto:

\[
\boxed{R_4\text{ está en BCNF}}
\]

### Relación \(R_5(CF,ING)\)

Dependencia:

\[
CF \rightarrow ING
\]

Clave candidata: \(\{CF\}\).

Por tanto:

\[
\boxed{R_5\text{ está en BCNF}}
\]

### Relación \(R_6(ING,EC)\)

Dependencia:

\[
ING \rightarrow EC
\]

Clave candidata: \(\{ING\}\).

Por tanto:

\[
\boxed{R_6\text{ está en BCNF}}
\]

### Relación \(R_7(NRO,HO,CF)\)

No presenta dependencias funcionales no triviales proyectadas sobre sus atributos, según las dependencias dadas. Su clave candidata es:

\[
\{NRO,HO,CF\}
\]

Como no hay dependencias no triviales que violen la condición de BCNF:

\[
\boxed{R_7\text{ está en BCNF}}
\]

## Paso 2. Descomposición final en BCNF

Todas las relaciones obtenidas en 3NF ya cumplen BCNF, por lo que no es necesario descomponerlas más.

La descomposición final es:

\[
\boxed{R_1(NRO,NC,IP,AI)}
\]
\[
\boxed{R_2(NC,NRO,NI)}
\]
\[
\boxed{R_3(IP,EC)}
\]
\[
\boxed{R_4(HO,ING)}
\]
\[
\boxed{R_5(CF,ING)}
\]
\[
\boxed{R_6(ING,EC)}
\]
\[
\boxed{R_7(NRO,HO,CF)}
\]

## Paso 3. Resumen de dependencias proyectadas y claves

| Relación | Dependencias funcionales proyectadas | Claves candidatas |
|---|---|---|
| \(R_1(NRO,NC,IP,AI)\) | \(NRO \rightarrow NC,IP,AI\); \(NC \rightarrow NRO,IP,AI\) | \(\{NRO\}\), \(\{NC\}\) |
| \(R_2(NC,NRO,NI)\) | \(NC \rightarrow NRO,NI\); \(NRO \rightarrow NC,NI\) | \(\{NC\}\), \(\{NRO\}\) |
| \(R_3(IP,EC)\) | \(IP \rightarrow EC\) | \(\{IP\}\) |
| \(R_4(HO,ING)\) | \(HO \rightarrow ING\) | \(\{HO\}\) |
| \(R_5(CF,ING)\) | \(CF \rightarrow ING\) | \(\{CF\}\) |
| \(R_6(ING,EC)\) | \(ING \rightarrow EC\) | \(\{ING\}\) |
| \(R_7(NRO,HO,CF)\) | Ninguna no trivial | \(\{NRO,HO,CF\}\) |

## Paso 4. ¿Se pierden dependencias funcionales?

No. Las nueve dependencias del cubrimiento minimal se preservan:

- \(NRO \rightarrow NC\), \(NRO \rightarrow IP\) y \(NRO \rightarrow AI\) se preservan en \(R_1\).
- \(NC \rightarrow NRO\) y \(NC \rightarrow NI\) se preservan en \(R_2\).
- \(IP \rightarrow EC\) se preserva en \(R_3\).
- \(HO \rightarrow ING\) se preserva en \(R_4\).
- \(CF \rightarrow ING\) se preserva en \(R_5\).
- \(ING \rightarrow EC\) se preserva en \(R_6\).

Por tanto:

\[
\boxed{\text{No se pierde ninguna dependencia funcional}}
\]

## Paso 5. Verificar el join sin pérdida

La descomposición BCNF conserva las mismas relaciones que la descomposición 3NF. Como \(R_7(NRO,HO,CF)\) contiene una clave candidata del esquema original, se mantiene la garantía de join sin pérdida.

\[
\boxed{\text{La descomposición en BCNF tiene join sin pérdida}}
\]

---

# Conclusión general

El ejercicio parte de un esquema universal que integra información sobre calles, planos, empresas constructoras, intendentes, hospitales, canchas e ingenieros.

A partir del enunciado, se identificaron las dependencias funcionales y se determinaron las dos claves candidatas del esquema universal:

\[
\boxed{\{NRO,HO,CF\}}
\]
\[
\boxed{\{NC,HO,CF\}}
\]

Después, se obtuvo un cubrimiento minimal de nueve dependencias funcionales. A partir de este cubrimiento, se construyó una descomposición en siete relaciones que cumple con 3NF, preserva las dependencias funcionales y garantiza el join sin pérdida.

Finalmente, se comprobó que todas las relaciones resultantes también cumplen con BCNF, porque los determinantes de las dependencias funcionales no triviales son superclaves de sus respectivas relaciones.

**Por lo tanto, para este conjunto de dependencias funcionales, la descomposición final en 3NF ya cumple con BCNF, sin perder dependencias funcionales y manteniendo el join sin pérdida.**
