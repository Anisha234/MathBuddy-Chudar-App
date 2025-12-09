# MathBuddy App

MathBuddy is an Android application created for Chudar to help students practice mathematics in an accessible, mobile-friendly way. It provides structured exercises, progress tracking, and AI-powered problem generation to support learning in low-resource environments.

---

## Overview

MathBuddy gives students a simple interface to practice Algebra, Number Theory, and Statistics, while allowing teachers and volunteers to introduce consistent, self-paced math practice. The app works offline for core features and syncs with AI services only when needed.

---

## Key Features

### 📚 Practice Modes
Students can work through topic-based practice sets, each containing:
- Multiple-choice questions  
- Instant feedback  
- Step-by-step explanations (from in-app solution banks)

### 🧑‍🏫 Screens & Components
- **Home Screen** — choose a topic or take a mixed practice test  
- **Practice Screen** — answer questions, view explanations, and progress  
- **Test Mode** — 20-question mixed-topic assessment  
- **User Progress** — stores completed problems, scores, and test history  
- **Local Database** — all progress and preset problems saved using Room

### 🤖 AI-Powered Functionality
MathBuddy integrates two external APIs:

#### **OpenAI API**
Used for:
- Generating new practice questions  
- Creating simple hints when students get stuck  
- Supporting future adaptive practice features  

#### **Wolfram Alpha API**
Used for:
- Checking correctness of mathematical expressions  
- Returning step-by-step evaluations or simplified results  
- Validating AI-generated questions

These APIs allow MathBuddy to expand beyond preset content while keeping learning grounded and accurate.

---

## Technology Overview

- **Kotlin + Android Jetpack**
- **Jetpack Compose UI** (if enabled in this version)
- **Room Database** for offline persistence
- **Repository Pattern** for clean data handling
- **OpenAI + Wolfram Alpha APIs** for intelligent question generation and solution checking

---

## Folder Structure (High-Level)

