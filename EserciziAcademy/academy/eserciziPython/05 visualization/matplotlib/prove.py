import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats

# Impostazioni dei parametri
x = np.linspace(-5, 5, 1000)

# 1. Distribuzione normale standard (σ=1, curtosi=0 per la normale)
mu1, sigma1 = 0, 1
y1 = stats.norm.pdf(x, mu1, sigma1)

# 2. Distribuzione con maggiore deviazione standard (σ=2, più piatta)
mu2, sigma2 = 0, 2
y2 = stats.norm.pdf(x, mu2, sigma2)

# 3. Distribuzione leptocurtica (più appuntita, minore varianza, curtosi positiva)
mu3, sigma3 = 0, 0.5
y3 = stats.norm.pdf(x, mu3, sigma3)

# Plot
plt.figure(figsize=(8, 5))
plt.plot(x, y1, label='Normale standard (σ=1)')
plt.plot(x, y2, label='Distribuzione più piatta (σ=2)')
plt.plot(x, y3, label='Distribuzione più appuntita (σ=0.5)')

# Etichette
plt.title("Effetti della deviazione standard e della curtosi")
plt.xlabel("Valori")
plt.ylabel("Densità di probabilità")
plt.legend(loc = 'upper left')
plt.grid(True)

# Mostra il grafico
plt.show()