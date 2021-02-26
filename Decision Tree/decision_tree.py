import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.tree import DecisionTreeClassifier 
from sklearn.metrics import confusion_matrix

dataset = pd.read_csv('arac_alma_data.txt')
X = dataset.iloc[:, [2,3]].values
y = dataset.iloc[:, 4].values
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.25, random_state = 0)
sc_X = StandardScaler()
X_train = sc_X.fit_transform(X_train)
X_test = sc_X.transform(X_test)
classifier = DecisionTreeClassifier(criterion = 'entropy', random_state=0)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
cm = confusion_matrix(y_test, y_pred)

print("################# Confusion Matrix #################")
print(cm)


print("################# Karşılaştırma #################")
print(" ")

counter=0
lost=0
for i,j in  zip(y_test,y_pred) :
  if(i!=j):   
    print('Gerçek ve Tahmin Uyuşmadı !!! Gerçek Değer : ',i,' Tahmin Değeri : ',j,' Index : ',counter)
    lost+=1
  counter+=1
print(" ")
print("#####################################################")
print(" ")

print('Toplam Test Edilen Veri Sayısı : ',counter)
print('Toplam Uyuşmama Sayısı : ',lost)
