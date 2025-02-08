import './App.css'
import React, { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Tabs, TabsList, TabsTrigger, TabsContent } from '@/components/ui/tabs';
import { User, LogOut } from 'lucide-react';

const Navigation = ({ currentUser, isAdmin, onLogout, onTabChange }) => {
  return (
    <div className="w-full bg-white border-b">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center">
        <h1 className="text-2xl font-bold">Cape Town Festival</h1>
        <div className="flex items-center gap-4">
          {currentUser && (
            <>
              <div className="flex items-center gap-2">
                <User className="w-5 h-5" />
                <span>{currentUser.name}</span>
              </div>
              <Button variant="ghost" size="sm" onClick={onLogout}>
                <LogOut className="w-4 h-4 mr-2" />
                Logout
              </Button>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

const App = () => {
  const [currentUser, setCurrentUser] = useState(null);
  const [isAdmin, setIsAdmin] = useState(false);

  const handleLogin = (userData) => {
    setCurrentUser(userData);
    setIsAdmin(userData.role === 'ADMIN');
  };

  const handleLogout = () => {
    setCurrentUser(null);
    setIsAdmin(false);
  };

  if (!currentUser) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <Card className="w-full max-w-md">
          <CardHeader>
            <CardTitle>Welcome to Cape Town Festival</CardTitle>
          </CardHeader>
          <CardContent>
            <Button
              className="w-full"
              onClick={() => handleLogin({ name: 'Test User', role: 'VISITOR' })}
            >
              Login as Visitor
            </Button>
            <Button
              className="w-full mt-2"
              variant="outline"
              onClick={() => handleLogin({ name: 'Admin', role: 'ADMIN' })}
            >
              Login as Admin
            </Button>
          </CardContent>
        </Card>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Navigation
        currentUser={currentUser}
        isAdmin={isAdmin}
        onLogout={handleLogout}
      />

      <main className="container mx-auto px-4 py-8">
        <Tabs defaultValue="events" className="w-full">
          <TabsList className="mb-4">
            <TabsTrigger value="events">Events</TabsTrigger>
            {isAdmin && <TabsTrigger value="dashboard">Dashboard</TabsTrigger>}
            {isAdmin && <TabsTrigger value="manage">Manage Events</TabsTrigger>}
          </TabsList>

          <TabsContent value="events">
            <Card>
              <CardHeader>
                <CardTitle>Available Events</CardTitle>
              </CardHeader>
              <CardContent>
                {/* Event list will go here */}
                <p className="text-gray-500">No events available</p>
              </CardContent>
            </Card>
          </TabsContent>

          {isAdmin && (
            <TabsContent value="dashboard">
              <Card>
                <CardHeader>
                  <CardTitle>Admin Dashboard</CardTitle>
                </CardHeader>
                <CardContent>
                  {/* Dashboard content will go here */}
                  <p className="text-gray-500">Dashboard coming soon</p>
                </CardContent>
              </Card>
            </TabsContent>
          )}

          {isAdmin && (
            <TabsContent value="manage">
              <Card>
                <CardHeader>
                  <CardTitle>Manage Events</CardTitle>
                </CardHeader>
                <CardContent>
                  {/* Event management will go here */}
                  <p className="text-gray-500">Event management coming soon</p>
                </CardContent>
              </Card>
            </TabsContent>
          )}
        </Tabs>
      </main>
    </div>
  );
};

export default App;
